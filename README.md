<p align="center">
      <img src="https://i.ibb.co/Ms80Jb5/istockphoto-1005374612-170667a.jpg width="726">
</p>


## About

Online system for your pets. In any time you can to enroll to the any doctor at any time and day. 
System connects with data base that let to save a little bit information about you and your pet.
Also system has separate tab with veterinarians and them specialty. In any time you have opportunity
to change your name, address, city or phone oh number, also pets has this function.

## Documentation

### DB queries
  - **`public List<Owner> showOwners()`** - Show all owners from database.
  - **`public Owner showOwner(int id)`** - Show current owner by their id.
  - **`public void editInfoOwner(int id, Owner updateOwner)`** - Updating current owner by new datas.
  - **`public List<Pet> show_PetVisits(int idOwner)`** - Show all pets of current owner.
  - **`public void savePet(Pet pet, int idOwner)`** - Saving pet and visit of current owner and take their id for node.
                                                                                        
### Controllers
  - **`public String ownerInfo(@PathVariable("id") int id, Model model)`** - Showing page of owner by id.
  - **`public String newOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult)`** - Showing page for insert owner to data base and checking by Hibernate validator.
  - **`public String vetShow(Model model)`** - Showing pages of all veterinarians.
  - **`public String editVetPage(@PathVariable("idVet") int idVet, Model model)`** - Page for edit of veterinar.

### Diagramm of data base
<p align="center">
      <img src="https://i.ibb.co/d4bwQYQ/bd.jpg width="726">
</p>

### A few photos
<p align="center">
      <img src="https://i.ibb.co/VNgzkG1/1.jpg width="726">
      <img src="https://i.ibb.co/vjj4vfK/6.jpg width="726">
      <img src="https://i.ibb.co/rp3cRnb/3.jpg width="726">
      <img src="https://i.ibb.co/vHk3pCV/4.jpg width="726">
      <img src="https://i.ibb.co/3h8YxQT/5.jpg width="726">
</p>
