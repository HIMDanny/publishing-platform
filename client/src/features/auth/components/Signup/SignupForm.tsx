import Field from '@components/Field/Field';
import Button from '@components/UI/Button';
import { Formik, Form } from 'formik';

const SignupForm = () => {
  return (
    <div className="flex flex-col items-center">
      <h3>Create Acccount</h3>
      <div className="my-8">Icons</div>
      <Formik
        initialValues={{
          name: '',
          email: '',
          password: '',
        }}
        onSubmit={() => {
          return;
        }}
      >
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
            size="lg"
            variant="secondary"
            text="Sign In"
          />
        </Form>
      </Formik>
    </div>
  );
};
export default SignupForm;
