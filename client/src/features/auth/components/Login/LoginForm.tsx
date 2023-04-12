import Field from '@components/Field/Field';
import Button from '@components/UI/Button';
import { Link } from 'react-router-dom';

const LoginForm = () => {
  return (
    <div className="flex flex-col items-center">
      <h3>Log In</h3>
      <div className="my-8">Icons</div>
      <form className="w-full max-w-sm text-center">
        <div className="flex flex-col gap-4">
          <Field />
          <input
            className="rounded-md border"
            type="text"
          />
        </div>
        <Link
          to="#"
          className="mt-7 mb-6 block"
        >
          Forgot your password?
        </Link>
        <Button
          size="lg"
          variant="secondary"
          text="Sign In"
        />
      </form>
    </div>
  );
};
export default LoginForm;
