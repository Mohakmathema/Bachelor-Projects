#name: Mohak Bhakta Mathema
#student_id: 2408249

import numpy as np #libraryto perform mathematical functions and equations
import matplotlib.pyplot as plt #plotting library for python to make visualizations
from scipy.optimize import root #importing root from scipy.optimize to find root of function

#iterative method

#function to plot the given equation/functions:
def plot_function(func, a, b, title='Function Plot'):
    x = np.linspace(a, b, 400) #creating array x with 400 values spaced evenly betn a and b
   #np.linespace() used as x-cord for plotting func.
    y = func(x) #func() = functin para in plot func. to calc y-cord of func. returns array
    plt.figure(figsize=(8, 4)) #creating figures #figsize = height and width in inches
    plt.plot(x, y, label='f(x)') #plt is matplotlib for plotting x and y
    plt.axhline(0, color='gray', lw=0.5) #y=0 is x-axis
    plt.title(title)
    plt.xlabel('x')
    plt.ylabel('f(x)')
    plt.grid(True)
    plt.show() #displaying plot

def bisection_method(func, a, b, tol=1e-6, max_iter=100):
    #func() is a function a and b are the initial endpoints where root is searched
    #tol = tolerance = small +ve num which is stop criteria for algo. (0.0000001)
    #max-iter = maximum num of times iterator can be done
    fa = func(a)
    fb = func(b)
    print(f"Function values at endpoints: f({a}) = {fa}, f({b}) = {fb}") #debugging the output
    root = a #root initially set to a
    n = 0  #creating loop/iterator counter and setting to 0
    while n < max_iter:
        root = (a + b) / 2 #finding root here
        froot = func(root)

        if abs(froot) < tol or (b - a) / 2 < tol:
            #1st: check if absolute value of froot is less than tol(condition true=froot) is very close to zero and this is good approx of true root
            #2nd: check if half of interval width(max error in root) conditon true = interval narrowed down and further will diminish returns
            return root #function termination if conditions applied

#narrowing down the interval:
        if func(a) * froot < 0: #check if values at a & root have opposite signs.true=function cross x-axis betn a & root . root exists in [a,root]
            b = root #half interval size to [a,root] cuz guarenteed
        else: #no function ar a and root have no opposite sign. no root betn a and root. so root betn [root,b]
            a = root #foucs interval to [root,b] cuz guarenteed

        n += 1  # Increasing n by 1(n=n+1)

    print("Warning exceeded the max num of iterations")
    return root #last computed root best estimate with given iterations

if __name__ == "__main__":
    #here we are defining the functions
    func1 = lambda x: x**2 - x - 1 #lambda = anonymous function lambda args:expression
    #these can have any number of args but only 1 expression writing small func w/o def
    func2 = lambda x: x**3 - x**2 - 2*x + 1

    #here we are plotting the functions
    plot_function(func1, -2, 3, 'Plot of f(x) = x^2 - x - 1')
    plot_function(func2, -3, 3, 'Plot of f(x) = x^3 - x^2 - 2x + 1')
#-2 and 3 are range of x-axis

    #here we are finding the  roots using bisectionMethod:Values Entered:
    root1_bisec = bisection_method(func1, 1, 2)
    root2_bisec = bisection_method(func2, -1, 1.5)
    #a and b are the endpoints of the intervals


#using scipy's root function to find roots of the equations
    #using scipy.optimize.root as reference
    sp_root1 = root(func1, 1.5).x.item() #second para/arg = initial guess
    sp_root2 = root(func2, 0).x.item() #.items() get the root as a scalar(single value)

    #displaying the results
    if root1_bisec is not None:
        print("Root of f(x) = x^2 - x - 1 found by Bisection Method: {:.3f}".format(root1_bisec))
    else:
        print("Failed to find root for f(x) = x^2 - x - 1 using Bisection Method")

    print("Root of f(x) = x^2 - x - 1 found by SciPy: {:.3f}".format(sp_root1))

    if root2_bisec is not None:
        print("Root of f(x) = x^3 - x^2 - 2x + 1 found by Bisection Method: {:.3f}".format(root2_bisec))
    else:
        print("Failed to find root for f(x) = x^3 - x^2 - 2x + 1 using Bisection Method")

    print("Root of f(x) = x^3 - x^2 - 2x + 1 found by SciPy: {:.3f}".format(sp_root2))
