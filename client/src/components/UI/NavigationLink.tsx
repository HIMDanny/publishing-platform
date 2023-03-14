export type NavigationLinkProps = {
  label: string;
  to: string;
};

const NavigationLink = ({ label, to }: NavigationLinkProps) => {
  return (
    <a
      href={to}
      className="block border-b border-b-transparent tracking-[.5px] text-gray-50 hover:border-b hover:border-gray-50 active:border-green-400 active:text-green-400"
    >
      {label}
    </a>
  );
};
export default NavigationLink;
