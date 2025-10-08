#include "Money.hpp"

Money::Money(double amount, const std::string& currency) : amount(amount), currency(currency) {}

double Money::getAmount() const {
    return amount;
}

std::string Money::getCurrency() const {
    return currency;
}

std::string Money::toString() const {
    std::ostringstream oss;
    oss << std::fixed << std::setprecision(2) << amount << " " << currency;
    return oss.str();
}
