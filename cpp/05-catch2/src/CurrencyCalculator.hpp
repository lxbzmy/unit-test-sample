#pragma once

#include "Money.hpp"
#include "RateService.hpp"
#include "CurrencyConversionException.hpp"
#include <string>
#include <memory>
#include <algorithm>
#include <cctype>
#include <cmath>

class CurrencyCalculator {
private:
    std::shared_ptr<RateService> rateService;

public:
    CurrencyCalculator(std::shared_ptr<RateService> rateService);
    Money convert(double amount, const std::string& fromCurrency, const std::string& toCurrency);
};
