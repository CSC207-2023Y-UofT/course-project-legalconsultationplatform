import argparse
import json

import data_preprocessing
import deserialization


def calculate_probability(client: dict, question: dict, attorney: dict):
    """
    :param client: a serialized JSON representation of the client entity
    :param question: a serialized JSON representation of the question entity
    :param attorney: a serialized JSON representation of the attorney entity
    :return: the predicted probability of client satisfaction using the provided
    machine learning model and client, question, and attorney info
    """
    model = deserialization.deserialize_model("lib/random_forest.pkl")
    data = data_preprocessing.data_preprocessing(client,
                                                 question,
                                                 attorney)
    return float(model.predict_proba(data)[:, 1])


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
