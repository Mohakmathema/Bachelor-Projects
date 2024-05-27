#name: Mohak Bhakta Mathema
#student_id: 2408249

import numpy as np
import matplotlib.pyplot as plt
import scipy.integrate as spi

def plot_function(func, a, b):
    """
    This function plots the graph of the input function
    within the given interval [a, b].
    """
    x = np.linspace(a, b, 400)
    y = func(x)
    plt.figure(figsize=(10, 5))
    plt.plot(x, y, label=f'f(x) in [{a}, {b}]')
    plt.title(f'Graph of the function')
    plt.xlabel('x')
    plt.ylabel('f(x)')
    plt.grid(True)
    plt.legend()
    plt.show()

def midpoint_approx(func, a, b, N):
    dx = (b - a) / N
    midpoints = np.linspace(a + dx/2, b - dx/2, N) #getting the midpoints
    midpoint_values = func(midpoints)
    result = np.sum(midpoint_values) * dx
    return result

if __name__ == "__main__":
    # Define the functions and their analytical antiderivatives
    func_1 = lambda x: x / (x**2 + 1)
    antiderivative_1 = lambda x: 0.5 * np.log(1 + x**2)  #integral

    func_2 = lambda x: np.exp(x) #numpy exponential e^x
    antiderivative_2 = lambda x: np.exp(x)  #integral

    #setting integration limits
    a1, b1 = 0, 5
    a2, b2 = 0, 5

    #plotting the functions in graph
    plot_function(func_1, a1, b1)
    plot_function(func_2, a2, b2)

    #defining the no of partitions(N) for both equations
    N1 = 500
    N2 = 500

    # Compute Midpoint Approximations
    midpoint_approx_1 = midpoint_approx(func_1, a1, b1, N1)
    midpoint_approx_2 = midpoint_approx(func_2, a2, b2, N2)

    # Calculate the true values using the analytical antiderivatives
    definite_integral_1 = antiderivative_1(b1) - antiderivative_1(a1)
    definite_integral_2 = antiderivative_2(b2) - antiderivative_2(a2)

    # Print the results

    print(f"Midpoint Approximation for 1st Function (x/(x^2+1)) = {midpoint_approx_1:.3f}")
    print(f"Actual Value for 1st Function (analytical) = {definite_integral_1:.3f}")
    print(f"Absolute error for 1st Function = {np.abs(midpoint_approx_1 - definite_integral_1):.4f}\n")

    print(f"Midpoint Approximation for 2nd Function (e^x) = {midpoint_approx_2:.3f}")
    print(f"Actual Value for 2nd Function (analytical) = {definite_integral_2:.3f}")
    print(f"Absolute error for 2nd Function = {np.abs(midpoint_approx_2 - definite_integral_2):.4f}")
