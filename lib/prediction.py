import argparse
import json
import data_preprocessing
import deserialization


def calculate_probability(client_dict: dict, question_dict: dict, attorney_dict: dict):
    """
    :param client_dict: a serialized JSON representation of the client entity
    :param question_dict: a serialized JSON representation of the question entity
    :param attorney_dict: a serialized JSON representation of the attorney entity
    :return: the predicted probability of client satisfaction using the provided
    machine learning model and client, question, and attorney info
    """
    model = deserialization.deserialize_model("lib/random_forest.pkl")
    data = data_preprocessing.data_preprocessing(client_dict,
                                                 question_dict,
                                                 attorney_dict)
    match1 = data_preprocessing.professional_match(question, attorney)
    match2 = data_preprocessing.location_match(client, attorney)
    return float(model.predict_proba(data)[:, 1] * 0.5 + match1 * 0.25 + match2 * 0.25)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--input', type=str, default=None)
    args = parser.parse_args()
    with open(args.input, 'r') as f:
        input_data = json.load(f)
    client = input_data['client']
    question = input_data['question']
    attorney = input_data['attorney']
    res = calculate_probability(client, question, attorney)
    print(json.dumps(res))
