#name: Mohak Bhakta Mathema

import numpy as np
import scipy.optimize
import matplotlib.pyplot as plt

#function to plot the given equation/functions:
def plot_function(func, a, b):
    x = np.linspace(a, b, 400) #to get x-cords of func
    y = func(x) #to get y-cords of function
    plt.plot(x, y) #matplotlib (plt) .plot to plot the x and y
    plt.axhline(0, color='red', lw=0.5) #horizontal lines
    plt.xlabel("x")
    plt.ylabel("f(x)")
    plt.title("Plot of the function")
    plt.grid(True)
    plt.show()


def newton_method(func, grad, x0, tol=1e-6, max_iter=100):
    #grad= derivative of f(x)/function
    #x0=initial guess betn the endpoints
    #tol[tolerance]= small +ve number[0.000001] i.e stop criteria for algo
    xn = x0 #setting value of xn as x0
    for n in range(max_iter):
        fxn = func(xn)
        if abs(fxn) < tol: #check if absolute function is close to zero
            print("Found solution after", n, "iterations.")
            return xn
        gxn = grad(xn) #calculating the derivative at xn
        if gxn == 0:#derivative=0 no solutions
            print("Zero derivative. No solution found.")
            return None
        xn = xn - fxn / gxn #N-R formula (xn+1) = xn - f(xn)/derivative of f(xn)
    print("Warning! Exceeded the maximum number of iterations.")
    return xn #returning the best guess


if __name__ == "__main__":
    # First Function and its derivative
    #lambda: anonymous expression/function small function
    func1 = lambda x: x ** 2 - x - 1
    grad1 = lambda x: 2 * x - 1

    # Plotting the first function
    #x-axis of graph 1
    plot_function(func1, -3, 3)

    # Newton's method initialization and call for the first function
    x0 = 1.5  # Initial guess for the first function
    our_root_1 = newton_method(func1, grad1, x0)

    # SciPy root finding for the first function
    sp_result_1 = scipy.optimize.root(func1, x0)
    sp_root_1 = sp_result_1.x.item() #.items() to get root as a scalar

    # Second Function and its derivative
    # lambda: anonymous expression/function small function
    func2 = lambda x: x ** 3 - x ** 2 - 2 * x + 1
    grad2 = lambda x: 3 * x ** 2 - 2 * x - 2

    # Plotting the second function
    #x-axis from a to b
    plot_function(func2, -3, 3)

    # Newton's method initialization and call for the second function
    x0 = 1.5  # Initial guess for the second function
    our_root_2 = newton_method(func2, grad2, x0)

    # SciPy root finding for the second function
    sp_result_2 = scipy.optimize.root(func2, x0)
    sp_root_2 = sp_result_2.x.item()

    # Printing the results
    print("1st root found by Newton's Method = {:0.3f}".format(our_root_1))
    print("1st root found by SciPy = {:0.3f}".format(sp_root_1))
    print("2nd root found by Newton's Method = {:0.3f}".format(our_root_2))
    print("2nd root found by SciPy = {:0.3f}".format(sp_root_2))
