import pandas as pd
import numpy as np
import text_preprocessing
import datetime as dt
import joblib


def data_preprocessing(client_dict, question_dict, attorney_dict):
    """
    :param client_dict: a dictionary representation of client entity
    :param question_dict: a dictionary representation of question entity
    :param attorney_dict: a dictionary representation of attorney entity
    :return: a
    """
    client_age = client_dict['age']
    client_num_household = client_dict['numberOfHousehold']
    client_income = client_dict['annualIncome']
    client_is_caucasian = int(client_dict['ethnicity'] == 'Caucasian')
    client_is_female = int(client_dict['gender'] == 'Female')

    question_sentiment = calculate_question_sentiment(question_dict)
    question_len = calculate_question_length(question_dict)
    question_emergency = is_emergency(question_dict)

    attorney_sentiment = calculate_attorney_sentiment(attorney_dict)
    attorney_len = calculate_attorney_length(attorney_dict)
    attorney_num_question = calculate_attorney_num_question(attorney_dict)
    attorney_answer_within = calculate_attorney_efficiency(attorney_dict)

    area_match = is_area_match(attorney_dict, question_dict)
    ddl_match = will_ddl_pass(attorney_dict, question_dict)
    distance = calculate_distance(attorney_dict, client_dict)

    client_is_divorced = int(client_dict['maritalStatus'] ==
                             'Divorced or Widowed')
    client_is_married = int(client_dict['maritalStatus'] == 'Married')
    client_is_single = int(client_dict['maritalStatus'] == 'Single')

    data = np.array([client_age, client_num_household, client_income,
                      client_is_caucasian, client_is_female, question_sentiment,
                      question_len, question_emergency, attorney_sentiment,
                      attorney_len, attorney_num_question,
                      attorney_answer_within, area_match, ddl_match, distance,
                      client_is_divorced, client_is_married, client_is_single])
    mask = np.isnan(data)
    data[mask] = 0
    data = data.reshape(1, -1)
    return standardize_data(data)


def professional_match(question: dict, attorney: dict) -> int:
    if not attorney['professionals']:
        return 0
    for professional in attorney['professionals']:
        if question['type'] == professional:
            return 1
    return 0


def location_match(client: dict, attorney: dict) -> int:
    return int(client['stateAbb'] == attorney['stateAbb'])


def standardize_data(input_data):
    """
    :param input_data: input data follows the sklearn API
    :return: the standardized data
    """
    scaler = joblib.load("lib/scaler.joblib")
    standardized_data = scaler.transform(input_data)
    return standardized_data


def calculate_question_sentiment(question_dict):
    """
    :return: the sentiment score for the initial post of the question
    """
    posts = question_dict['posts']

    # check if the post list is empty
    if not posts:
        return 0

    # for non-empty list
    init_question = posts[0]
    post_text = init_question['postText']
    return text_preprocessing.sentiment_score(post_text)


def calculate_question_length(question_dict):
    """
    :return: the length of the initial post of the question
    """
    posts = question_dict['posts']

    if not posts:
        return 0

    init_question = posts[0]
    post_text = init_question['postText']
    return text_preprocessing.text_len(post_text)


def ddl_at(question_dict):
    """
    :return: return the time the question was created and the legal deadline of
    that question
    """
    create_at = question_dict['createAt']
    create_at = convert_date(create_at)

    ddl = question_dict['legalDeadline']
    ddl = convert_date(ddl)

    return ddl - create_at


def is_emergency(question_dict):
    """
    :return: return true iff the question is a emergency quesiton, i.e., has a
    legal deadline within 14 days
    """

    return int(ddl_at(question_dict) < dt.timedelta(days=14))


def get_attorney_posts(attorney_dict):
    """
    :return: a list of post_dict that belong to the attorney
    """
    question_list = attorney_dict['questionsList']
    post_list = []

    # check if the question list is empty
    if not question_list:
        return post_list

    for question_dict in question_list:
        posts = question_dict['posts']
        for post in posts:
            if post['belongsTo'] == attorney_dict['userId']:
                post_list.append(post)
    return post_list


def calculate_attorney_sentiment(attorney_dict):
    """
    :return: the average sentiment of all posts belong to this attorney
    """
    post_list = get_attorney_posts(attorney_dict)
    sentiment = 0

    # check if the post list is empty
    if not post_list:
        return 0

    for post in post_list:
        post_text = post['postText']
        sentiment += text_preprocessing.sentiment_score(post_text)
    return sentiment / len(post_list)


def calculate_attorney_length(attorney_dict):
    """
    :return: the average length of all posts belong to this attorney
    """
    post_list = get_attorney_posts(attorney_dict)
    length = 0

    if not post_list:
        return 0

    for post in post_list:
        post_text = post['postText']
        length += text_preprocessing.text_len(post_text)
    return length / len(post_list)


def calculate_attorney_num_question(attorney_dict):
    """
    :return: the number of question the attorney has answered
    """
    return len(attorney_dict['questionsList'])


def calculate_attorney_efficiency(attorney_dict):
    """
    :return: the average answering time of the attorney
    """
    question_list = attorney_dict['questionsList']
    avg_answering_time = 14
    time = dt.timedelta()

    # check if the question list is empty
    if not question_list:
        return avg_answering_time

    for question_dict in question_list:
        time += convert_date(question_dict['takenAt']) - \
                convert_date(question_dict['createAt'])
    return (time / len(question_list)).total_seconds() / (24 * 3600)


def get_attorney_expertise(attorney_dict):
    """
    :return: the question type that the attorney answered most frequently
    """
    question_list = attorney_dict['questionsList']

    if not question_list:
        return 'type not exist'

    type_list = pd.Series([question_dict['type'] for question_dict in
                          question_list])
    return type_list.describe()['top']


def is_area_match(attorney_dict, question_dict):
    """
    :return: return true iff the question type match with the type that the
    attorney answered most frequently
    """
    return int(get_attorney_expertise(attorney_dict) == question_dict['type'])


def will_ddl_pass(attorney_dict, question_dict):
    """
    :return: return true iff the attorney typically answered the question
    before the question's ddl
    """
    return int((ddl_at(question_dict).total_seconds()) / (24 * 3600) < \
        calculate_attorney_efficiency(attorney_dict))


def calculate_distance(attorney_dict, client_dict):
    """
    :return: the physical distance between client and attorney based on their
    postal code. Note: this is a simplified version of distance measure due to
    the resources constraint
    """
    return abs(int(attorney_dict['postalCode']) -
               int(client_dict['postalCode']))


def convert_date(date: str):
    if date is None:
        date = np.nan
    return pd.to_datetime(date)
