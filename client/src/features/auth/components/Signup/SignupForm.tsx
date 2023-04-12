import Button from '@components/UI/Button';

const SignupForm = () => {
  return (
    <div className="flex flex-col items-center">
      <h3>Create Acccount</h3>
      <div className="my-8">Icons</div>
      <form className="w-full max-w-sm text-center">
        <div className="mb-6 flex flex-col gap-4">
          <input
            className="rounded-md border"
            type="email"
          />
          <input
            className="rounded-md border"
            type="text"
          />
          <input
            className="rounded-md border"
            type="text"
          />
        </div>
        <Button
          size="lg"
          variant="secondary"
          text="Sign In"
        />
      </form>
    </div>
  );
};
export default SignupForm;
