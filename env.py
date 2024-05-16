import socket
import sys
import winreg


def is_valid_ip(ip):
    try:
        socket.inet_aton(ip)
        return True
    except socket.error:
        return False


def set_environment_variable(name, value):
    key = winreg.OpenKey(winreg.HKEY_CURRENT_USER,
                         'Environment', 0, winreg.KEY_ALL_ACCESS)
    winreg.SetValueEx(key, name, 0, winreg.REG_EXPAND_SZ, value)
    winreg.CloseKey(key)


env_value = sys.argv[1]
if is_valid_ip(env_value) == False:
    print("IP地址不合法")
    sys.exit(1)

set_environment_variable('MYSQL_SERVER', env_value+':3306')
set_environment_variable('NACOS_SERVER', env_value+':8848')
set_environment_variable('REDIS_SERVER', env_value)
