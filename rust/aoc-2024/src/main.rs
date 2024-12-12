use std::fs;

fn main() {
    println!("Hello, world!");

    let contents = fs::read_to_string("src/inputs/input1.txt").expect("Something went wrong reading the file");

    println!("Text: \n{contents}");

}
