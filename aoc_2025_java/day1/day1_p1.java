package aoc_2025.day1;

import common.MyFileReader;

void main(){
    
    MyFileReader myFileReader = new MyFileReader();

    var inputs = myFileReader.readFile("./input.txt");
    for(String line : inputs){
        IO.print(line);
    }

}