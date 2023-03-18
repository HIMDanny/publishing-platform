import { Link } from 'react-router-dom';
import Logo from '@components/Logo';
import HeaderActions from './HeaderActions';
import HeaderNavigation from './HeaderNavigation';

const Header = () => {
  return (
    <header className="py-6">
      <div className="container mx-auto grid grid-cols-3 items-center">
        <Link
          to="/"
          className="w-fit"
        >
          <Logo />
        </Link>

        <HeaderNavigation />

        <HeaderActions />
      </div>
    </header>
  );
};
export default Header;
