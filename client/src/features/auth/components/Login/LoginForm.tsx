import Field from '@components/Field/Field';
import Button from '@components/UI/Button';
import { Link } from 'react-router-dom';
import { Formik, Form } from 'formik';
import AuthIcons from '../AuthIcons';
import { SigninSchema } from '@features/auth/utils/validation-schemas';

const LoginForm = () => {
  return (
    <div className="flex flex-col items-center">
      <h3>Log In</h3>
      <AuthIcons />
      <Formik
        validationSchema={SigninSchema}
        initialValues={{
          email: '',
          password: '',
        }}
        onSubmit={() => {
          return;
        }}
      >
        {({ isValid }) => (
          <Form className="w-full max-w-sm text-center">
            <div className="flex flex-col gap-4">
              <Field
                type="email"
                name="email"
                label="Email"
                labelProps={{ className: 'bg-gray-50' }}
              />
              <Field
                type="password"
                name="password"
                label="Password"
                labelProps={{ className: 'bg-gray-50' }}
              />
            </div>
            <Link
              to="#"
              className="mt-7 mb-6 block"
            >
              Forgot your password?
            </Link>
            <Button
              disabled={!isValid}
              size="lg"
              variant="secondary"
              text="Sign In"
            />
          </Form>
        )}
      </Formik>
    </div>
  );
};
export default LoginForm;
