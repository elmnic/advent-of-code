// use crate::days::day1;
use crate::days::day2;
use std::fs;

mod days;
mod utils;

fn main() {
    let day_number = "2";
    // let file_name = format!("src/inputs/example{day_number}.txt");
    let file_name = format!("src/inputs/input{day_number}.txt");
    let contents =
        fs::read_to_string(file_name).expect("Something went wrong reading the file");

    let input: Vec<&str> = contents.lines().collect::<Vec<_>>();

    // let result1 = day1(input);
    // println!("Day 1: {:?}", result1);

    let result2 = day2(input);
    println!("Day 2: {:?}", result2);
}
