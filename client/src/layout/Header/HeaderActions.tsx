import Button from '@components/UI/Button';
import NavigationLink from '@components/UI/NavigationLink';
import { Link } from 'react-router-dom';

const HeaderActions = () => {
  return (
    <div className="flex items-center gap-3 justify-self-end">
      <NavigationLink
        label="Log In"
        to="/auth?mode=login"
      />
      <Button
        component={Link}
        text="Sign Up"
        to="/auth?mode=signup"
      />
    </div>
  );
};
export default HeaderActions;
