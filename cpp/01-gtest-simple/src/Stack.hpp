#pragma once

#include <vector>
#include <stdexcept>

template <typename T>
class Stack {
private:
    std::vector<T> elements;
public:
    void push(const T& item) {
        elements.push_back(item);
    }

    T pop() {
        if (elements.empty()) {
            throw std::out_of_range("Stack is empty");
        }
        T item = elements.back();
        elements.pop_back();
        return item;
    }

    size_t size() const {
        return elements.size();
    }

    bool empty() const {
        return elements.empty();
    }
};
