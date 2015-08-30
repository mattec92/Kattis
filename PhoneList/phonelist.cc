/* Mattias Cederlund, mattec92@gmail.com */

#include <iostream>
#include <stdio.h>
#include <algorithm>

using namespace std;

bool compare(string first, string second) {
	if (first.size() < second.size()) {
		for (int i = 0; i < first.size(); i++) {
			if (first[i] != second[i]) {
				return false;
			}
		}
		return true;
	}
	return false;
}

int main() {
	int cases, numbers;
	scanf("%d", &cases);
	string num[10001];
	for (int c = 0; c < cases; c++) {
		scanf("%d", &numbers);
		for (int n = 0; n < numbers; n++) {
			cin >> num[n];
		}
		sort(num, num+numbers);
		bool allok = true;
		for (int i = 0; i < numbers-1; i++) {
			if (compare(num[i], num[i+1])) {
				allok = false;
				break;
			}
		}
		if (allok == true) {
			cout << "YES" << endl;
		}
		else {
			cout << "NO" << endl;
		}
	}
	return 0;
}
