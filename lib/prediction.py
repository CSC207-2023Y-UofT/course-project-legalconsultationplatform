import argparse
import json

import data_preprocessing
import deserialization


def calculate_probability(client, question, attorney):
    """
    :param client: a serialized JSON representation of the client entity
    :param question: a serialized JSON representation of the question entity
    :param attorney: a serialized JSON representation of the attorney entity
    :return: the predicted probability of client satisfaction using the provided
    machine learning model and client, question, and attorney info
    """
    client_dict = deserialization.deserialize_entity(client)
    question_dict = deserialization.deserialize_entity(question)
    attorney_dict = deserialization.deserialize_entity(attorney)
    return predict(client_dict, question_dict, attorney_dict)


def predict(client: dict, question: dict, attorney: dict):
    model = deserialization.deserialize_model("lib/random_forest.pkl")
    input_data = data_preprocessing.data_preprocessing(client,
                                                       question,
                                                       attorney)
    return float(model.predict_proba(input_data)[:, 1])


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--input', type=str, default=None)
    args = parser.parse_args()
    input_data = json.loads(args.input)
    client = input_data['client']
    question = input_data['question']
    attorney = input_data['attorney']
    res = calculate_probability(client, question, attorney)
    print(json.dumps(res))
