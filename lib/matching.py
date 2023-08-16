import argparse
import json
import ast
import numpy as np
from scipy.optimize import linear_sum_assignment
from typing import Set


def matching_algo(problems, attorneys, edge_weights):
    num_problems = len(problems)
    num_attorneys = len(attorneys)

    # Adjust matrix to be square if necessary
    max_dim = max(num_problems, num_attorneys)
    cost_matrix = np.full((max_dim, max_dim), np.inf)  # Initialize with large values

    for i, problem in enumerate(problems):
        for j, attorney in enumerate(attorneys):
            # Negate the weights for maximization and handle missing weights
            cost_matrix[i, j] = -edge_weights.get((problem, attorney), np.inf)

    matching_results = []
    n = 0

    while n < min(5, len(attorneys)):
        # If all values in the cost_matrix are infinity, break
        if np.all(np.isinf(cost_matrix)):
            break

        row_ind, col_ind = linear_sum_assignment(cost_matrix)

        for i, j in zip(row_ind, col_ind):
            if i < num_problems and j < num_attorneys:  # Ensure we don't consider padded values
                matching_results.append((problems[i], attorneys[j]))
                cost_matrix[i, j] = np.inf  # Mark the matched pair with a large cost
        n += 1

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

