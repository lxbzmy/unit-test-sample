const { expect } = require('chai');
const { fizzBuzz } = require('../src/fizzbuzz2');

describe('fizzBuzz', () => {
  it('returns 1 for 1', () => {
    expect(fizzBuzz(1)).to.equal('1');
  });

  it('returns Fizz for multiples of 3', () => {
    expect(fizzBuzz(3)).to.equal('Fizz');
  });

  it('returns Buzz for multiples of 5', () => {
    expect(fizzBuzz(5)).to.equal('Buzz');
  });

  it('returns FizzBuzz for multiples of 15', () => {
    expect(fizzBuzz(15)).to.equal('FizzBuzz');
  });
});
