import csv

# Initialize a dictionary to store rule, description, and occurence count
rules_dict = {}

# Open CSV file and count rule occurrences
with open('sonarqube_issues.csv', 'r') as f:
    reader = csv.DictReader(f)
    for row in reader:
        if row['rule'] not in rules_dict:
            rules_dict[row['rule']] = {'message': row['message'], 'count': 0}
        rules_dict[row['rule']]['count'] += 1

# Sort descending
sorted_rules = sorted(rules_dict.items(), key=lambda item: item[1]['count'], reverse=True)

# Write the results to a new CSV file
with open('sonarqube_rules_count.csv', 'w', newline='') as f:
    fieldnames = ['Rule Key', 'Rule Name', '#']
    writer = csv.DictWriter(f, fieldnames=fieldnames)

    writer.writeheader()
    for rule, details in sorted_rules:
        writer.writerow({
            'Rule Key': rule, 
            'Rule Name': details['message'], 
            '#': details['count']
        })