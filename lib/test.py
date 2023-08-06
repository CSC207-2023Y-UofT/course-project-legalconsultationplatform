from test2 import add
import argparse
import json

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--aa', type=str, default=None)
    args = parser.parse_args()

    a = json.loads(args.aa)

    res = add(a)
    b = {"res": res}
    print(json.dumps(b))
