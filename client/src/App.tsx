import Authentication from 'pages/Authentication';
import CategoriesPage from 'pages/Categories';
import HomePage from 'pages/Home';
import RootLayout from 'pages/Root';
import WritePage from 'pages/Write';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      { index: true, element: <HomePage /> },
      {
        path: 'categories',
        element: <CategoriesPage />,
      },
      {
        path: 'write',
        element: <WritePage />,
      },
    ],
  },
  {
    path: '/auth',
    element: <Authentication />,
  },
]);

const App = () => {
  return <RouterProvider router={router} />;
};

export default App;
