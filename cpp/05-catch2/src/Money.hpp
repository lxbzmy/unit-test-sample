#pragma once

#include <string>
#include <iomanip>
#include <sstream>

class Money {
private:
    double amount;
    std::string currency;

public:
    Money(double amount, const std::string& currency);
    double getAmount() const;
    std::string getCurrency() const;
    std::string toString() const;
};
