use itertools::Itertools;
use regex::Regex;
use std::iter::zip;

pub fn day1(input: Vec<&str>) -> (i64, i64) {
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

fn parse_capture(capture: &str) -> i64 {
    capture.parse().unwrap()
}
