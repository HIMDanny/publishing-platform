import NavigationLink from '@components/UI/NavigationLink';
import navigation from 'data/navigation';

const NavigationList = () => {
  return (
    <ul className="flex gap-10 text-gray-50">
      {navigation.map((link) => (
        <li key={link.label}>
          <NavigationLink
            label={link.label}
            to={link.to}
          />
        </li>
      ))}
    </ul>
  );
};
export default NavigationList;
