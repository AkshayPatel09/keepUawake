import numpy as np
import tensorflow as tf
import cv2
import base64
from os.path import dirname, join
def main(strImg):

#     decodeit = open('hello_level.jpeg', 'wb')
#     decodeit.write(base64.b64decode((strImg)))
#     decodeit.close()
    im_bytes = base64.b64decode((strImg))
    im_arr = np.frombuffer(im_bytes, dtype=np.uint8)
    image = cv2.imdecode(im_arr, flags=cv2.IMREAD_COLOR)
#     image = cv2.imread('hello_level.jpeg')
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades+'haarcascade_frontalface_default.xml')
    faces = face_cascade.detectMultiScale(gray, 1.1)
    if len(faces) == 0:
        return -1

    (x, y, w, h) = faces[0]
    cropped_image = gray[y:y + h, x:x + w]
    cropped_image = cv2.resize(cropped_image, dsize=(120, 120), interpolation=cv2.INTER_AREA)
    img = np.array(cropped_image, dtype="float32")
    img = np.reshape(img, (1,120,120,1))
    filename = join(dirname(__file__), "model_8.tflite")
    interpreter = tf.lite.Interpreter(model_path=filename)
#     interpreter = tf.lite.Interpreter(model_path="../ml/model.tflite")
    interpreter.allocate_tensors()
    input_details = interpreter.get_input_details()
    output_details = interpreter.get_output_details()

    # Test the model on random input data.
    input_shape = input_details[0]['shape']

#     print("*"*50, input_details)
    interpreter.set_tensor(input_details[0]['index'], img)

    interpreter.invoke()

    # The function `get_tensor()` returns a copy of the tensor data.
    # Use `tensor()` in order to get a pointer to the tensor.
    output_data = interpreter.get_tensor(output_details[0]['index'])[0]
    output_data = np.argmax(output_data)
    print(output_data)
    return output_data