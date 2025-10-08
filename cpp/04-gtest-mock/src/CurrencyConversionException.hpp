#pragma once

#include <stdexcept>
#include <string>

class CurrencyConversionException : public std::runtime_error {
public:
    CurrencyConversionException(const std::string& message) : std::runtime_error(message) {}
    CurrencyConversionException(const std::string& message, const std::exception& cause) : std::runtime_error(message + ": " + cause.what()) {}
};
