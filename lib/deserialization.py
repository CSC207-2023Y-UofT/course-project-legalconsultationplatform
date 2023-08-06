import json
import joblib
import sklearn


def deserialize_entity(json_string):
    """
    :param json_string: a serialized JSON representation of the entity
    :return: a deserialized dictionary representation of the entity
    """
    entity = json.loads(json_string)
    return entity


def deserialize_model(file_path):
    """
    :param file_path: a serialized file path of the machine learning model
    :return: a deserialized machine learning model
    """
    model = joblib.load(file_path)
    return model
