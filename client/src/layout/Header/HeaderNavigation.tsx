import NavigationLink from '@components/UI/NavigationLink';
import navigation from 'data/navigation';

const HeaderNavigation = () => {
  return (
    <nav className="w-fit justify-self-center rounded-full border-2 border-gray-50 py-[0.875rem] px-[3.75rem]">
      <ul className="flex gap-10 text-gray-50">
        {navigation.map((link) => (
          <li
            key={link.label}
            className="relative"
          >
            <NavigationLink
              label={link.label}
              to={link.to}
            />
          </li>
        ))}
      </ul>
    </nav>
  );
};
export default HeaderNavigation;
