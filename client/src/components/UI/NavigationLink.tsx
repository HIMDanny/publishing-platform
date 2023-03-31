import { Link } from 'react-router-dom';

export type NavigationLinkProps = {
  label: string;
  to: string;
};

const NavigationLink = ({ label, to }: NavigationLinkProps) => {
  return (
    <Link
      to={to}
      className="relative block tracking-[.5px] text-gray-50 after:absolute after:bottom-0 after:left-0 after:h-px after:w-full after:origin-bottom-right after:scale-0 after:bg-gray-50 after:transition-transform after:duration-500 after:ease-out hover:after:origin-bottom-left hover:after:scale-100 active:border-green-400 active:text-green-400 after:active:bg-green-400"
    >
      {label}
    </Link>
  );
};
export default NavigationLink;
