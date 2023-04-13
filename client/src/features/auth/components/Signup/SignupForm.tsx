import Field from '@components/Field/Field';
import Button from '@components/UI/Button';
import { Formik, Form } from 'formik';
import AuthIcons from '../AuthIcons';
import { SignupSchema } from '@features/auth/utils/validation-schemas';

const SignupForm = () => {
  return (
    <div className="flex flex-col items-center">
      <h3>Create Acccount</h3>
      <AuthIcons />
      <Formik
        validationSchema={SignupSchema}
        initialValues={{
          name: '',
          email: '',
          password: '',
        }}
        onSubmit={() => {
          return;
        }}
      >
        {({ isValid }) => (
          <Form className="w-full max-w-sm text-center">
            <div className="mb-6 flex flex-col gap-4">
              <Field
                type="text"
                label="Name"
                name="name"
                labelProps={{ className: 'bg-gray-50' }}
              />
              <Field
                type="email"
                label="Email"
                name="email"
                labelProps={{ className: 'bg-gray-50' }}
              />
              <Field
                type="password"
                label="Password"
                name="password"
                labelProps={{ className: 'bg-gray-50' }}
              />
            </div>
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
export default SignupForm;
