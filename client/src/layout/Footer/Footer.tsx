import NavigationList from '../NavigationList';
import SocialsList from './SocialsList';

const Footer = () => {
  const copyrightYear = new Date().getFullYear();

  return (
    <footer className="bg-gray-800 pb-9 pt-16 text-white">
      <div className="container">
        <div className="flex items-center justify-between">
          <NavigationList />
          <SocialsList />
        </div>
        <p className="mt-4 text-center">Copyright - {copyrightYear}</p>
      </div>
    </footer>
  );
};
export default Footer;
