#include "CurrencyCalculator.hpp"

CurrencyCalculator::CurrencyCalculator(std::shared_ptr<RateService> rateService) : rateService(rateService) {}

Money CurrencyCalculator::convert(double amount, const std::string& fromCurrency, const std::string& toCurrency) {
    if (amount < 0) {
        throw CurrencyConversionException("Invalid amount: must be positive");
    }

    if (fromCurrency.empty() || toCurrency.empty()) {
        throw CurrencyConversionException("Currency codes cannot be empty");
    }

    std::string from = fromCurrency;
    std::string to = toCurrency;
    std::transform(from.begin(), from.end(), from.begin(), ::toupper);
    std::transform(to.begin(), to.end(), to.begin(), ::toupper);

    // 相同货币直接返回
    if (from == to) {
        return Money(amount, to);
    }

    // 获取汇率
    auto fromRate = rateService->getRate(from);
    auto toRate = rateService->getRate(to);

    if (!fromRate) {
        throw CurrencyConversionException("Exchange rate not found for: " + from);
    }

    if (!toRate) {
        throw CurrencyConversionException("Exchange rate not found for: " + to);
    }

    // 转换计算
    double convertedAmount = std::round(amount * (*toRate) / (*fromRate) * 100) / 100;
    return Money(convertedAmount, to);
}
