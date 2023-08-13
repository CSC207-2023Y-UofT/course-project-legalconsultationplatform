import re
from nltk.tokenize import word_tokenize
from nltk.stem import PorterStemmer
from nltk.sentiment import SentimentIntensityAnalyzer


def remove_special(text):
    """
    :return: a string without special characters and url
    """
    # Remove URLs
    text = re.sub(r"http\S+|www\S+|https\S+", "", text, flags=re.MULTILINE)

    # Remove numbers and special characters
    text = re.sub(r"[^a-zA-Z\s\'!?]", "", text)

    return text


def text_preprocessing(text):
    """
    :return: a cleaned string with only lower case, without any special
    characters and with word being stemmed
    """
    stemmer = PorterStemmer()

    # Lowercase
    text = text.lower()

    # remove special characters
    text = remove_special(text)

    # Tokenization
    tokens = word_tokenize(text)

    # Stemming
    tokens = [stemmer.stem(token) for token in tokens]

    # Join tokens back into a single string
    cleaned_text = ' '.join(tokens)

    return cleaned_text


def sentiment_score(text):
    """
    :param text: raw text of the post
    :return: the sentiment score of the text based on Vader sentiment lexicon
    """
    sia = SentimentIntensityAnalyzer()
    text = text_preprocessing(text)
    return sia.polarity_scores(text)['compound']


def text_len(text):
    """
    :param text: raw text of the post
    :return: the length of the text
    """
    text = text_preprocessing(text)
    return len(word_tokenize(text))
