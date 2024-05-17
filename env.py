import socket
import subprocess
import sys


def is_valid_ip(ip):
    try:
        socket.inet_aton(ip)
        return True
    except socket.error:
        return False


def set_environment_variable(name, value):
    subprocess.run(f"setx {name} {value}", stdout=subprocess.DEVNULL,
                   stderr=subprocess.DEVNULL, shell=True)


env_value = sys.argv[1]
if is_valid_ip(env_value) == False:
    print("IP地址不合法")
    sys.exit(1)

set_environment_variable('MYSQL_SERVER', env_value+':3306')
set_environment_variable('NACOS_SERVER', env_value+':8848')
set_environment_variable('REDIS_SERVER', env_value)
