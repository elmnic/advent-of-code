use itertools::Itertools;
use regex::Regex;
use std::iter::zip;

pub fn day1(input: Vec<&str>) -> (i64, i64) {
    fn parse_capture(capture: &str) -> i64 {
        capture.parse().unwrap()
    }

    let re = Regex::new(r"(\d+)\s{3}(\d+)").unwrap();

    let parsed_input: Vec<_> = input
        .iter()
        .flat_map(|line| {
            println!("line: {}", line);
            re.captures_iter(line)
                .map(|caps| {
                    println!("extract: {:?}", caps.get(1));
                    return match caps.extract().1 {
                        [l, r] => vec![parse_capture(l), parse_capture(r)],
                    };
                })
                .collect::<Vec<Vec<i64>>>()
        })
        .collect();

    let transposed_input = crate::utils::transpose(parsed_input.clone());

    let sorted: Vec<Vec<i64>> = transposed_input
        .iter()
        .map(|x| x.clone().into_iter().sorted().collect())
        .collect::<Vec<_>>();

    let pairs: Vec<_> = zip(&sorted[0], &sorted[1]).collect();
    let distances: Vec<i64> = pairs
        .iter()
        .map(|pairs| match pairs.clone() {
            (l, r) => (l - r).abs(),
        })
        .collect();

    let part2: Vec<i64> = sorted[0]
        .iter()
        .map(|x| x * (sorted[1].iter().filter(|y| y.clone() == x).count() as i64))
        .collect();

    // println!("transposed_input: {:?}", transposed_input);
    // println!("Sorted: {:?}", sorted);
    // println!("Pairs: {:?}", pairs);
    // println!("distances: {:?}", distances);

    (distances.iter().sum(), part2.iter().sum())
}

pub fn day2(input: Vec<&str>) -> (i64, i64) {
    fn check_safe(report: &Vec<i64>) -> bool {
        // println!("report: {:?}", report);
        let is_ascending = report.is_sorted_by(|l, r| l < r);
        let is_descending = report.is_sorted_by(|l, r| l > r);
        let is_close_enough = report.is_sorted_by(|left, right| {
            let diff = (left.clone() - right.clone()).abs();
            let is_safe = diff >= 1 && diff <= 3;
            if !is_safe {
                println!("\tleft: {:?}, right: {:?}, diff: {:?}, is_safe: {:?}", left, right, diff, is_safe);
            }
            is_safe
        });
        // println!("is_ascending: {:?}", is_ascending);
        // println!("is_descending: {:?}", is_descending);
        // println!("is_close_enough: {:?}", is_close_enough);
        let is_safe = (is_ascending || is_descending) && is_close_enough;
        // println!("Report {:?} is_safe: {:?}",report, is_safe);
        is_safe
    }

    let parsed_input: Vec<_> = input
        .iter()
        .map(|line| {
            line.split(" ")
                .map(|x| x.parse::<i64>().unwrap())
                .collect::<Vec<_>>()
        })
        .collect();

    let result1 = parsed_input
        .iter()
        .map(|line| check_safe(line))
        .filter(|x| *x)
        .count();

    (result1 as i64, 0)
}
