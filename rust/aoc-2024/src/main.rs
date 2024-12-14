use crate::days::day1;
use std::fs;

mod days;
mod utils;

fn main() {
    let contents =
        fs::read_to_string("src/inputs/input1.txt").expect("Something went wrong reading the file");

    let input: Vec<&str> = contents.lines().collect::<Vec<_>>();

    let result1 = day1(input);
    
    println!("Part 1: {:?}", result1);
}
