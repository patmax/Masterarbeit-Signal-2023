import requests
import csv

# API Endpoint
base_url = "http://localhost:9000/api/issues/search?componentKeys=SignalServer&scopes=MAIN&severities=MINOR,MAJOR,CRITICAL,BLOCKER&languages=java"
page_size = 500
page = 1

payload={}
headers = {
  'Authorization': 'Bearer sqa_c5df92fa56efd959a2af6482c5634443eece8fef'
}

# Prepare data for CSV
data = []

while True:
    url = f"{base_url}&p={page}&ps={page_size}"
    response = requests.request("GET", url, headers=headers, data=payload).json()
    
    # Extract issues from the response
    issues = response["issues"]

    for issue in issues:
        component = issue['component'].replace('SignalServer:', '')  # remove 'SignalServer:' prefix
        data.append({
            'rule': issue['rule'],
            'component': component,
            'line': issue['line'],
            'message': issue['message'],
            'severity': issue['severity'],  
            'type': issue['type']
        })
        
    # Check if there are more pages to process
    if len(issues) < page_size:
        break
    else:
        page += 1

# Write data to CSV
with open('sonarqube_issues.csv', 'w', newline='') as f:
    writer = csv.DictWriter(f, fieldnames=data[0].keys())
    writer.writeheader()
    writer.writerows(data)
