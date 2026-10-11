    List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 30, 10, 20, 40, 33, 0, 13, 8);
		System.out.println("Even No : " + list.stream().filter(f -> f % 2 == 0).toList());
		System.out.println("Greater than 10 : " + list.stream().filter(f -> f > 10).toList());
		System.out.println("Sum of all the number : " + list.stream().reduce(0, Integer::sum));
//		In this code, reduce() is a terminal operation that combines all elements of a stream into a single summary result,
//		while 0 acts as the starting point (the identity value) for that accumulation.

		System.out.println("Find the maximum and minimum number. : " + list.stream().min((a , b) -> a - b));
		System.out.println("Find the maximum and max number. : " + list.stream().max((a , b) -> a - b));
//		In Java, Stream.min() and Stream.max() are terminal operations used to find the smallest or largest elements in a stream.
//		Because object streams require a sorting strategy, both methods take a Comparator as an argument and return an Optional to safely handle empty streams.

		List<String> stringList = List.of("neeti", "ram", "varun", "ajay", "savita", "neha", "pooja");
		System.out.println("Convert all strings to uppercase. : " + stringList.stream().map(String::toUpperCase).toList());

		System.out.println("Sort numbers in ascending. : " + list.stream().sorted().toList());
		System.out.println("Sort numbers in descending order.. : " + list.stream().sorted(Comparator.reverseOrder()).toList());
//		Stream.sorted() method is an intermediate operation

		System.out.println("Remove duplicate elements from a list. : " + list.stream().distinct().toList());
		System.out.println("Count the number of elements in a list. : " + list.stream().count());
//		Stream.count() method is a terminal operation

		System.out.println("Find the first element that starts with 'A'. : " + stringList.stream().filter(f -> f.startsWith("a")).findFirst());
		System.out.println("Find the square of each number. : " + list.stream().map(m -> m * m).toList());

//		Find duplicate elements in a list.
		Set<Integer> seen = new HashSet<>();
		List<Integer> duplicate = list.stream().filter(f -> !seen.add(f)).distinct().toList();
		System.out.println("Find duplicate elements in a list. : " + duplicate);

		System.out.println("Find the second-highest number in a list. : " + list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst());
		System.out.println("Find the second-lowest number. : " + list.stream().distinct().sorted().skip(1).findFirst());

//		Count the frequency of each number.
		Map<Integer, Long> fhash = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("Count the frequency of each number. : " + fhash);
//		In Java, Function.identity() is a built-in helper method that returns a lambda function which simply returns its own input argument.
//		Instead of transforming the data, it says: "Whatever object you give me, I will pass it right back to you without changing it."

//		Find the first non-repeating character in a string.
		String word = "neeti";
		Map<Character, Integer> mapHash = new HashMap<>();
		for(Character st : word.toCharArray()) {
			mapHash.put(st, mapHash.getOrDefault(st, 0) + 1);
		}
		for(Character st : word.toCharArray()) {
			if(mapHash.get(st) == 1) {
				System.out.println("first non-repeating character in a string : " + st);
				break;
			}
		}

//		Find the first repeating character in a string
		for(Character st : word.toCharArray()) {
			if(mapHash.get(st) > 1) {
				System.out.println("first repeating character : " + st);
				break;
			}
		}

//		Find common elements between two lists.
		List<String> list1 = Arrays.asList("Java", "Spring", "Hibernate", "HTML");
		List<String> list2 = Arrays.asList("Python", "Java", "HTML", "C++");
		List<String> commonList = list1.stream().filter(list2::contains).toList();
		System.out.println("Find common elements between two lists. : " + commonList);

		System.out.println("Find the top three largest numbers. : " + list.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).toList());
		System.out.println("Group strings by their length. : " + stringList.stream().collect(Collectors.groupingBy(String::length)));
		System.out.println("Count the occurrences of each character in a string. : " + mapHash);

//		Find the highest-paid employee.
		List<Employee> employeeList = Arrays.asList(
				new Employee("Alice", 75000, "HR", 1),
				new Employee("Bob", 120000, "IT", 2),
				new Employee("Charlie", 95000, "Admin", 3),
				new Employee("Diana", 145000, "HR", 4),
				new Employee("Evan", 110000, "IT", 5)
		);

		Optional<Employee> highestPaidEmp = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary));
		System.out.println("highest-paid employee : " + highestPaidEmp.get());

//		Find the second-highest-paid employee.
		Optional<Employee> secondHighestPaidEmp = employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();
		System.out.println("Find the second-highest-paid employee. : " + secondHighestPaidEmp);

		System.out.println("Group employees by department. : " + employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)));

		System.out.println("Find the highest-paid employee in each department. : " + employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))));

//		Find the average salary by department.
		Map<String, Double> averageSalaryByDep = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("Find the average salary by department. : " + averageSalaryByDep);

		System.out.println("Sort employees by salary and then by name. : " + employeeList.stream().sorted(
				Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName)).toList());

		Optional<Map.Entry<String, Double>> maxDeptSalary = averageSalaryByDep.entrySet().stream().max(Map.Entry.comparingByValue());
		System.out.println("Find the department with the highest average salary. : " + maxDeptSalary);

//		Partition employees into salary-above-50,000 and salary-at-or-below-50,000 groups.
		List<Employee> salaryAbove = employeeList.stream().filter(f -> f.getSalary() > 50000).toList();
		List<Employee> salaryAtOrAbove = employeeList.stream().filter(f -> f.getSalary() >= 50000).toList();
		System.out.println("salary-above-50,000 : " + salaryAbove);
		System.out.println("salary-at-or-above-50,000 : " + salaryAtOrAbove);

//		Convert a list of employees into a Map using employee ID as the key.
		Map<Integer, Employee> safeMap = employeeList.stream().collect(Collectors.toMap(Employee::getEmpId, Function.identity()));
		System.out.println("A list of employees into a Map using employee ID as the key : " + safeMap);

//		Flatten a list of lists using flatMap().
		List<List<String>> nestedList = Arrays.asList(
				Arrays.asList("Java", "Spring"),
				Arrays.asList("Docker", "Kubernetes"),
				Arrays.asList("AWS", "Azure")
		);
		System.out.println("list of lists using flatMap : " + nestedList.stream().flatMap(Collection::stream).toList());
