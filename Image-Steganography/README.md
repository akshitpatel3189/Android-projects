

# Image Steganography
Steganography is the process of hiding a secret message within a larger one in such a way that someone  cannot know the presence or contents of the hidden message. Although related, Steganography is not to be confused with Encryption, which is the process of making a message unintelligible—Steganography attempts to hide the existence of communication.
The main advantage of the steganography algorithm is because of its simple security mechanism. Because the steganographic message is integrated invisibly and covered inside other harmless sources, it is very difficult to detect the message without knowing
the existence and the appropriate encoding scheme.

# Documentation

#### ImageSteganography Class

| Java attribute     | Java set methods                | Description                                                  |
| :---------------- | :------------------------------ | :----------------------------------------------------------- |
| Message | setMessage(...) , getMessage() | Set the value of the message, Get the value of the message. |
| Secret_Key | setSecret_key(...) | Set the value of secret key. |
| Image  | setImage(...) | Set the value of image.              |
| Encoded_Image | getEncoded_image() | Get the value of the encoded image after text encoding. |
| Encoded | isEncoded() | Check that the encoding is over or not |
| Decoded | isDecoded() | Check whether the decoding is over or not. |
| SecretKeyWrong | isSecretKeyWrong() | Check that the secret key provided was right or wrong after decoding was done. |


### Example App

There are two options `Encode` and `Decode`. In the encode section you can hide a secret message into an image without making any noticeable changes. In the decode section you can extract the message from the encoded image by inserting the correct key.

**Note** - After pressing the `Save` button, both the original and encoded images are saved at the location ```Android/data/com.akshit.steganography/files/Documents/<UUID>/ ```

|                            Encode                            |                            Decode                            |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="https://github.com/aagarwal1012/Image-Steganography-Library-Android/blob/master/images/encode.gif" height = "500px"/> | <img src="https://github.com/aagarwal1012/Image-Steganography-Library-Android/blob/master/images/decode.gif" height = "500px"/> |





