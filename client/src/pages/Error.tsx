import Logo from '@components/UI/Logo';
import { Link, isRouteErrorResponse, useRouteError } from 'react-router-dom';

const ErrorPage = () => {
  const error = useRouteError();

  let title = 'Oops... Something wrong happened!';
  let message = 'Check your internet connection';

  if (isRouteErrorResponse(error)) {
    if (error.status >= 500) {
      message = error.data.message;
    }

    if (error.status === 404) {
      title = 'Not found!';
      message = 'Could not find resource or page.';
    }
  }

  return (
    <>
      <header className="absolute top-0 w-full py-5">
        <nav className="container">
          {/* TODO: change to dark variant */}
          <Link
            to="/"
            className="block w-fit"
          >
            <Logo />
          </Link>
        </nav>
      </header>
      <main className="background-error flex h-screen items-center justify-center">
        <div className="container text-center">
          {/* TODO: can be changed to heading and paragraph, but heading styles should be added in theme */}
          <h3>{title}</h3>
          <h5 className="mt-5 text-gray-600">{message}</h5>
        </div>
      </main>
    </>
  );
};
export default ErrorPage;
