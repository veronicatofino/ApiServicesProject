import json
import datetime
import http.client
from time import time

########################################################################################################################
##################################################### ENVIRONMENTS #####################################################
########################################################################################################################

# local
#conn = http.client.HTTPConnection("localhost:8080")

# vagrant
conn = http.client.HTTPConnection("192.168.56.120:8080")

# container
# conn = http.client.HTTPConnection("localhost:5000")

########################################################################################################################
######################################################## LOGIN #########################################################
########################################################################################################################
"""
login_post = {
    'username': 'blue',
    'password': '123456'
}
json_data_post = json.dumps(login_post)
conn.request("POST", "/login", json_data_post, headers={'Content-type': 'application/json'}) 

"""



########################################################################################################################
######################################################## CONCEPTS #########################################################
########################################################################################################################

#################################################
########## Encontrar un usuario por id ##########
#################################################

# headers = {
#     'Content-type': 'application/json',
#     'authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJibGFjayIsImlkZW50aXR5IjoiYmxhY2siLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjAyMDI0MzY2LCJleHAiOjE2MDIwMjQ5NjZ9.GhUpEW5xthqzcSIGVGUDjZA4UHhmDn5nlO6laM_d54BIkQhmL4qdM4cDLLLEQoXGG-G5AuvU5a4oEXiQOid0lg'
# }
# conn.request("GET", "/users/1", headers=headers)

############################################
########## Crear un nuevo concepto ##########
############################################

headers = {
    'Content-type': 'application/json',
    'authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJibGFjayIsImlkZW50aXR5IjoiYmxhY2siLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjAyMDI0MzY2LCJleHAiOjE2MDIwMjQ5NjZ9.GhUpEW5xthqzcSIGVGUDjZA4UHhmDn5nlO6laM_d54BIkQhmL4qdM4cDLLLEQoXGG-G5AuvU5a4oEXiQOid0lg'
}

concept_post = {
"pxordx" : "D",
 "oldPxordx" : "D",
 "codeType" : "I09",
 "conceptClassId" : "4-dig billing code",
 "conceptId" : 44832029,
 "vocabularyId" : "ICD9CM",
 "domainId" : "Condition",
 "track" : "Medical",
 "standardConcept" : "",
 "code" : "0029",
 "codeWithPeriods" : "002.9",
 "codeScheme" : "ICD9DIAG",
 "longDesc" : "Paratyphoid fever, unspecified",
 "shortDesc" : "Paratyphoid fever, unspecified",
 "codeStatus" : "A",
 "codeChange" : "No change",
 "codeChangeYear" : 2015,
 "codePlannedType" : "UP",
 "codeBillingStatus" : "Y",
 "codeCmsClaimStatus" : "Y",
 "sexCd" : "F",
 "anatOrCond" : "C",
 "poaCodeStatus" : "N",
 "poaCodeChange" : "No change",
 "poaCodeChangeYear" : 2015,
 "idStartDate" : "19700101",
 "idEndDate" : "20991231",
 "invalidReason": "",
 "createDt" : "41005"
 }
json_data_post = json.dumps(concept_post)
conn.request("POST", "/concepts/create", json_data_post, headers=headers)

############################################


start = datetime.datetime.now()
res = conn.getresponse()
end = datetime.datetime.now()

data = res.read()

elapsed = end - start

print(data.decode("utf-8"))
print("\"" + str(res.status) + "\"")
print("\"" + str(res.reason) + "\"")
print("\"elapsed seconds: " + str(elapsed) + "\"")

