const { fizzBuzz } = require('../src/fizzbuzz2');

describe('fizzBuzz', () => {
  it('returns 1 for 1', () => {
    expect(fizzBuzz(1)).toBe('1');
  });

  it('returns Fizz for multiples of 3', () => {
    expect(fizzBuzz(3)).toBe('Fizz');
  });

  it('returns Buzz for multiples of 5', () => {
    expect(fizzBuzz(5)).toBe('Buzz');
  });

  it('returns FizzBuzz for multiples of 15', () => {
    expect(fizzBuzz(15)).toBe('FizzBuzz');
  });
});
