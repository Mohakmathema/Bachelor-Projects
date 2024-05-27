#name: Mohak Bhakta Mathema
#student_id: 2408249

import numpy as np
import matplotlib.pyplot as plt
import scipy.integrate as spi

def plot_function(func, a, b):
    x = np.linspace(a, b, 400)
    y = func(x)
    plt.plot(x, y)
    plt.axhline(0, color='red', lw=0.5)
    plt.xlabel("x")
    plt.ylabel("f(x)")
    plt.title("Plot of the function")
    plt.grid(True)
    plt.show()


def trapezoidal_approx(func, a, b, N):
    dx = (b - a) / N #getting small change in x as dx
    x = np.linspace(a, b, N + 1) #generating N+1 values betn a and b
    return 0.5 * dx * np.sum(func(x[:-1]) + func(x[1:]))
                        #adds the function values at the left and right endpoints of each subinterval[gives sum of height of parallel lines of trapezoids]
# average of the lengths of the two parallel sides[0.5]
if __name__ == "__main__":
    # First function to be integrated
    func_1 = lambda x: x / (x ** 2 + 1)
    antiderivative_1 = lambda x: 0.5 * np.log(np.abs(1 + x ** 2))
#log = how many times must a base/number be multiplied by itself to get a requirednumber

    # Second function to be integrated
    func_2 = lambda x: np.exp(x)
    antiderivative_2 = lambda x: np.exp(x)

    # End points for functions
    a1, b1 = 0, 5
    a2, b2 = 0, 5

    # Plot the graph of the functions
    plot_function(func_1, a1, b1)
    plot_function(func_2, a2, b2)

    # Number of partitions
    N1 = 500
    N2 = 500

    # Compute Trapezoidal Approximations:
    trapezoidal_approx_1 = trapezoidal_approx(func_1, a1, b1, N1)
    trapezoidal_approx_2 = trapezoidal_approx(func_2, a2, b2, N2)

    # Calculate the true value of the definite integrals
    definite_integral_1 = antiderivative_1(b1) - antiderivative_1(a1)
    definite_integral_2 = antiderivative_2(b2) - antiderivative_2(a2)

    # Calculate the absolute error between the approximate value and true value
    error_1 = np.abs(trapezoidal_approx_1 - definite_integral_1)
    error_2 = np.abs(trapezoidal_approx_2 - definite_integral_2)

    # Print the results
    print(f"Trapezoidal Approximation for 1st Function = {trapezoidal_approx_1:.3f}")
    print(f"Actual Value for 1st Function = {definite_integral_1:.3f}")
    print(f"Absolute error for 1st Function = {error_1:.4f}")

    print(f"Trapezoidal Approximation for 2nd Function = {trapezoidal_approx_2:.3f}")
    print(f"Actual Value for 2nd Function = {definite_integral_2:.3f}")
    print(f"Absolute error for 2nd Function = {error_2:.4f}")
