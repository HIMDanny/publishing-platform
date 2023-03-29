import SearchField from '@components/UI/SearchField';
import CategorySection from './CategorySection';

const CategorySections = () => {
  return (
    <>
      <div className="my-14 flex justify-center">
        <SearchField />
      </div>
      <CategorySection label="A" />
      <CategorySection label="B" />
      <CategorySection label="C" />
    </>
  );
};
export default CategorySections;
