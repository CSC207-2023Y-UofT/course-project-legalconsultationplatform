import argparse
import json
import ast
import numpy as np
from scipy.optimize import linear_sum_assignment
from typing import Set


def matching_algo(problems, attorneys, edge_weights):
    """
    :param problems: a list of question id to match
    :param attorneys: a list of attorney id to match
    :param edge_weights: a dictionary that maps a question id - attorney id
    pairs to the probability of customer satisfaction
    :return:
    """
    num_problems = len(problems)
    num_attorneys = len(attorneys)

    # Create the cost matrix
    cost_matrix = np.zeros((num_problems, num_attorneys))
    for i, problem in enumerate(problems):
        for j, attorney in enumerate(attorneys):
            # Negate the weights for maximization
            cost_matrix[i, j] = -edge_weights[(problem, attorney)]

    # Perform linear sum assignment multiple times to enforce the one-to-five
    # matching constraint
    matching_results = []
    used_attorneys: Set[int] = set()  # To track attorneys that have been assigned

    while len(matching_results) < 5 * num_problems:
        row_ind, col_ind = linear_sum_assignment(cost_matrix)

        # Generate matching results based on the assignments
        for i, j in zip(row_ind, col_ind):
            used_attorneys.add(attorneys[j])

            # If we've used all attorneys, we reset the used_attorneys set
            if len(used_attorneys) == num_attorneys:
                used_attorneys.clear()

            # Set the cost of the assigned attorney to a large value only if
            # they haven't been used for all problems yet
            if attorneys[j] not in used_attorneys:
                cost_matrix[i, j] = np.inf

    return matching_results


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--input', type=str, default=None)
    args = parser.parse_args()
    with open(args.input, 'r') as f:
        input_data = json.load(f)
    questions = input_data['questions']
    attorneys = input_data['attorneys']
    weights = input_data['weights']
    weights = {(tuple(ast.literal_eval(key))): value for key, value in weights.items()}
    matching = matching_algo(questions, attorneys, weights)
    print(json.dumps(matching))

