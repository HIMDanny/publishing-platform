import Button from '@components/UI/Button';
import NavigationLink from '@components/UI/NavigationLink';

const HeaderActions = () => {
  return (
    <div className="flex items-center gap-3 justify-self-end">
      <NavigationLink
        label="Log In"
        to="#"
      />
      <Button>Sign Up</Button>
    </div>
  );
};
export default HeaderActions;
