import argparse
import json
import subprocess

parser = argparse.ArgumentParser(description='Help to docker')
parser.add_argument('host', type=str)
parser.add_argument('username', type=str)
parser.add_argument('password', type=str)
args = parser.parse_args()
tmp = subprocess.run("docker images --format json",
                     shell=True, stdout=subprocess.PIPE)
jsonStr = tmp.stdout.decode("utf-8")
results = jsonStr.split("\n")
results.pop()
for item in results:
    result = json.loads(item)
    repo = result["Repository"]
    if "k8s" in repo or "flannel" in repo or "dms" in repo:
        image = f"{repo}:{result['Tag']}"
        tmp = subprocess.run(f"docker save {image} | bzip2 | sshpass -p {args.password} ssh {args.username}@{args.host} docker load",
                             shell=True)
        print(f"Successful share image {image}")
pass
