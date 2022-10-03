
w = "http:''"
r=requests.get(w, verify=False)

r = requests.get(w, verify=False, timeout=10)

