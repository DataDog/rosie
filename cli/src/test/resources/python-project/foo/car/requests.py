from requests import get
r = get(w, verify=True)
r = get(w, verify=True, timeout=10)
