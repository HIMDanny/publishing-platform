import NavigationLink from '@components/UI/NavigationLink';

export type CategorySectionProps = {
  label: string;
};

const categories = [
  'smartphones',
  'laptops',
  'fragrances',
  'skincare',
  'groceries',
  'home-decoration',
  'furniture',
  'tops',
  'womens-dresses',
  'womens-shoes',
  'mens-shirts',
  'mens-shoes',
  'mens-watches',
  'womens-watches',
  'womens-bags',
  'womens-jewellery',
  'sunglasses',
  'automotive',
  'motorcycle',
  'lighting',
];

const CategorySection: React.FC<CategorySectionProps> = ({ label }) => {
  const categoryList = (
    <ul className="grid grid-cols-4 gap-y-8 gap-x-4">
      {categories.map((category, idx) => (
        <li
          key={idx}
          className="w-fit"
        >
          <NavigationLink
            label={category}
            to="/"
          />
        </li>
      ))}
    </ul>
  );

  return (
    <section className="border-b-2 border-gray-300 pb-20 last:border-none">
      <h3 className="my-10">{label}</h3>
      {categoryList}
    </section>
  );
};
export default CategorySection;
