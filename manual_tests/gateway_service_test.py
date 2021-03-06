import json
import datetime
import http.client
from time import time

########################################################################################################################
##################################################### ENVIRONMENTS #####################################################
########################################################################################################################

#local
#conn = http.client.HTTPConnection("localhost:9999")

# vagrant
conn = http.client.HTTPConnection("192.168.56.121:9999")

########################################################################################################################
######################################################## LOGIN #########################################################
########################################################################################################################


def login():
    headers_default = {'Content-type': 'application/json'}


    login_post = {
        "username": "avocado",
        "password": "123"
    }
    json_data_post = json.dumps(login_post)
    conn.request("POST", "/api/gateway/usersservice/login", json_data_post, headers={'Content-type': 'application/json'})
    
    res = conn.getresponse()
    data = res.read()

    data_json = json.loads(data.decode("utf-8"))
    print(data_json)
    if 'token' in data_json:
        jwt_token = data_json['token']
    else:
        print("Token not found")
        exit(0)

    headers = {
        'Content-type': 'application/json',
        'authorization': jwt_token
    }

    # headers = {
    #     'Content-type': 'application/json'
    # }

    return headers

headers = login()

#######################
#### Users Service ####
#######################

# conn.request("GET", "/api/gateway/usersservice/users", headers=headers)
#conn.request("POST", "/users?name=MrRed&email=mrred@gmail.com", headers={'Content-type': 'application/json'})
print()
########################
#### Concepts Service ####
########################

conn.request("GET", "/api/gateway/conceptsservice/concepts/getConceptsByGeneralParams?vocabularyId=ICD9CM", headers=headers)

start = datetime.datetime.now()
res = conn.getresponse()
end = datetime.datetime.now()

data = res.read()

elapsed = end - start

print(data.decode("utf-8"))
print("\"" + str(res.status) + "\"")
print("\"" + str(res.reason) + "\"")
print("\"elapsed seconds: " + str(elapsed) + "\"")

